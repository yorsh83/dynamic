package com.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.EvalRequest;
import com.test.model.Formula;
import com.test.repo.FormulaRepository;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@RestController
@RequestMapping("/api/formulas")
public class FormulaController {

	@Autowired
	private FormulaRepository repository;

	@PostMapping("/evaluate")
	public ResponseEntity<?> evaluateFormula(@RequestBody EvalRequest request) {
		Optional<Formula> formulaOpt = repository.findById(request.getFormulaId());
		if (!formulaOpt.isPresent())
			return ResponseEntity.notFound().build();

		Formula formula = formulaOpt.get();
		try {
			ExpressionBuilder builder = new ExpressionBuilder(formula.getExpresion());
			builder.variables(request.getVariables().keySet());
			Expression expression = builder.build();
			request.getVariables().forEach(expression::setVariable);
			double result = expression.evaluate();
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error evaluando fórmula: " + e.getMessage());
		}
	}

	@GetMapping
	public List<Formula> listarFormulas() {
		return repository.findByActivoTrue();
	}
	@GetMapping("/version")
	public String version() {
		return "1.0";
	}
}

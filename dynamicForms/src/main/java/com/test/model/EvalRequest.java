package com.test.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvalRequest {

	private Long formulaId;
	private Map<String, Double> variables;
}

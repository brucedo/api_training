package ca.vertigoshirley.echo.controllers.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.vertigoshirley.echo.serde.Operands;

@RestController
@RequestMapping("/api")
public class Math {
    

    @PostMapping
    @RequestMapping("/mul")
    public Map<String, Double> multiply
    (
        @RequestBody Operands operands
    )
    {
        Map<String, Double> answer = new HashMap<>();
        answer.put("answer", operands.x * operands.y);

        return answer;
    }

    @PostMapping
    @RequestMapping("/add")
    public Map<String, Double> add
    (
        @RequestBody Operands operands
    )
    {
        Map<String, Double> answer = new HashMap<>();
        answer.put("answer", operands.x + operands.y);

        return answer;
    }

    @PostMapping
    @RequestMapping("/div")
    public Map<String, Double> div
    (
        @RequestBody Operands operands
    )
    {
        Map<String, Double> answer = new HashMap<>();
        answer.put("answer", operands.x / operands.y);

        return answer;
    }

    @PostMapping
    @RequestMapping("/sub")
    public Map<String, Double> sub
    (
        @RequestBody Operands operands
    )
    {
        Map<String, Double> answer = new HashMap<>();
        answer.put("answer", operands.x - operands.y);
        
        return answer;
    }
}

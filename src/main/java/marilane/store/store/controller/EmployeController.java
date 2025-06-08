package marilane.store.store.controller;

import lombok.RequiredArgsConstructor;
import marilane.store.store.service.EmployeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/employes")
public class EmployeController {

    private final EmployeService employeService;

}


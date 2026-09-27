package com.jsp.employee_crud.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.employee_crud.Dao.Emp_Repository;
import com.jsp.employee_crud.Entity.Employee;


@Controller
public class Emp_Controller {

	@Autowired
	Emp_Repository employeeRepository;
	
	@GetMapping("/")
	public String loadMain() {
		return "main.html";
	}
	
	@GetMapping("/add")
	public String add() {
		return "add.html";
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute Employee employee, RedirectAttributes attributes) {
		employeeRepository.save(employee);
		attributes.addFlashAttribute("message","data Saved successfully");
		return "redirect:/";
	}
	
	@GetMapping("/view")
	public String viewAll(RedirectAttributes attributes, ModelMap map) {
		List<Employee> list = employeeRepository.findAll();
		if(list.isEmpty()) {
			attributes.addFlashAttribute("message","No records present");
			return "redirect:/";
		}else {
			map.put("list", list);
			return "view.html";
			
		}
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam int id, RedirectAttributes attributes) {
		employeeRepository.deleteById(id);
		attributes.addFlashAttribute("message","data deleted success");
		return "redirect:/view";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int id, ModelMap map) {
		Employee employee = employeeRepository.findById(id).orElseThrow();
		map.put("emp", employee);
		return "edit.html";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Employee employee, RedirectAttributes attributes) {
		employeeRepository.save(employee);
		attributes.addFlashAttribute("message","Data Updated Success");
		return "redirect:/view";
	}
}

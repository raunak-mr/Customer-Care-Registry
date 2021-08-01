package com.example.demo.controller;

import com.example.demo.model.Complaint;
import com.example.demo.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @GetMapping("/adminhome")
    public String viewHomePage(Model model) {
        model.addAttribute("listComplaints",complaintService.getAllComplaints());
        return "index";
    }

    @GetMapping("/showNewComplaintForm")
    public String showNewComplaintForm(Model model) {
        Complaint complaint = new Complaint();
        model.addAttribute("complaint",complaint);
        return "new_complaint";
    }

    @PostMapping("/saveComplaint")
    public String saveComplaint(@ModelAttribute("complaint") Complaint complaint) {
        complaintService.saveComplaint(complaint);
        return "redirect:/adminhome";
    }

    @GetMapping("/showFormForUpdate/{complaintId}")
    public String showFormForUpdate(@PathVariable(value = "complaintId") long complaintId, Model model) {
        Complaint complaint = complaintService.getComplaintById(complaintId);
        model.addAttribute("complaint", complaint);
        return "update_complaint";
    }

//    @GetMapping("/showComplaintStatus/{complaintId}")
//    public String showComplaintStatus(@PathVariable(value = "complaintId") long complaintId,Model model) {
//        Complaint complaint = complaintService.getComplaintByStatus(complaintId);
//        model.addAttribute("complaint", complaint);
//        return "status_Complaint";
//    }

    @GetMapping("/deleteComplaint/{complaintId}")
    public String deleteComplaint(@PathVariable(value = "complaintId") long complaintId) {
        this.complaintService.deleteComplaintById(complaintId);
        return "redirect:/adminhome";
    }
}

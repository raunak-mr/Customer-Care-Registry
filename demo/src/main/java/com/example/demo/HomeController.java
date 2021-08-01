package com.example.demo;

import com.example.demo.model.Complaint;
import com.example.demo.model.Person;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
//    @Autowired
//    private Complaint complaint;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ComplaintRepository complaintRepository;
    @Autowired
    ComplaintService complaintService;
    private String tempEmail;

    @GetMapping("/")
    public String welcomePage() {
        return "welcome";

    }

    @GetMapping("/login")
    public String loginPage() {
        return"login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return"signup";
    }

    @GetMapping("/admin")
    public String loginAdminPage() {
        return"admin";
    }

    @GetMapping("/complaint")
    public String complaintPage() {
        return"complaint";
    }

    @PostMapping("/signupSubmit")
    public  String signupSubmitPage(Person person) {
        customerRepository.save(person);
        return"login";
    }

    @PostMapping("/complaintSubmit")
    public String complaintSubmitPage(Complaint complaint) {
        tempEmail = complaint.getEmail();
        complaintRepository.save(complaint);
        return "home";
    }

    @GetMapping("/track")
    public ModelAndView statusTrackPage() {
        ModelAndView mav = new ModelAndView("status");
        mav.addObject("complaints", complaintRepository.findByEmail(tempEmail));
//        mav.addObject("complaints", complaintService.ge);
        return mav;

    }


    @PostMapping("/contactus")
    public String contactPage() {
        return "contact";
    }

    @PostMapping("/loginSubmit")
    public String loginSubmitPage(@RequestParam String email, @RequestParam String password) {
        Person tempPerson;
        tempPerson = customerRepository.findByEmail(email);
        if(tempPerson!=null && tempPerson.getPassword().equals(password)) {
            return"home";
        }
        else {
            return"login";
        }

    }

    @PostMapping("/loginAdmin")
    public ModelAndView loginAdminPage(@RequestParam String email,@RequestParam String password) {
        ModelAndView mav=new ModelAndView("adminhome");
        mav.addObject("listComplaints",complaintService.getAllComplaints());
        return mav;
    }

    @GetMapping("/loginstatus")
    public String statuslogintrackPage() {
        return "tracklogin";
    }

    @PostMapping("/statushomey")
    public String statusloginPage(String email) {
        Complaint tempemail;
        tempemail=complaintRepository.findByEmail(tempEmail);
        if(tempemail!=null ) {
            return"display";
        }
        else {
            return "complaint";
        }
    }

}

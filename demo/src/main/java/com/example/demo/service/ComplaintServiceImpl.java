package com.example.demo.service;

import com.example.demo.model.Complaint;
import com.example.demo.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintServiceImpl implements ComplaintService{

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    Complaint complaint;

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    @Override
    public void saveComplaint(Complaint complaint) {
        this.complaintRepository.save(complaint);
    }

    @Override
    public Complaint getComplaintById(long complaintId) {
        Optional<Complaint> optional = complaintRepository.findById(complaintId);
        Complaint complaint = null;
        if(optional.isPresent()) {
            complaint = optional.get();
        } else {
            throw new RuntimeException("Complaint Not Found for Complaint Id ::"+complaintId);
        }
        return complaint;
    }


//    @Override
//    public Complaint getComplaintByEmail(String email) {
//        Optional<Complaint> optional = Optional.ofNullable(complaintRepository.findByEmail(email));
//        Complaint complaint = null;
//        if(optional.isPresent()) {
//            complaint = optional.get();
//        } else {
//            throw new RuntimeException("Complaint Not Found for Customer Email ::"+email);
//        }
//        return complaint;
//    }

    @Override
    public Complaint getComplaintByStatus(long complaintId) {
        Optional<Complaint> optional = complaintRepository.findById(complaintId);
        Complaint complaint = null;
        if(optional.isPresent()) {
            complaint = optional.get();
        } else {
            throw new RuntimeException("Complaint Not Found for Complaint Id ::"+complaintId);
        }
        return complaint;
    }

    @Override
    public void deleteComplaintById(long complaintId) {
        this.complaintRepository.deleteById(complaintId);
    }
}

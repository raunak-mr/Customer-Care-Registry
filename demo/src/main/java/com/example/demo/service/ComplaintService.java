package com.example.demo.service;

import com.example.demo.model.Complaint;

import java.util.List;

public interface ComplaintService {
    List<Complaint> getAllComplaints();
    void saveComplaint(Complaint complaint);
    Complaint getComplaintById(long complaintId);
//    Complaint getComplaintByEmail(String email);
    Complaint getComplaintByStatus(long complaintId);
    void deleteComplaintById(long complaintId);
}

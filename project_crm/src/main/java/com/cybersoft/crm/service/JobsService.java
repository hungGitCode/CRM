package com.cybersoft.crm.service;

import com.cybersoft.crm.model.JobsModel;
import com.cybersoft.crm.model.QualitiesModel;
import com.cybersoft.crm.repository.JobsRepository;

import java.util.List;

public class JobsService {
    private JobsRepository jobsRepository;
    public JobsService(JobsRepository jobsRepository){
        this.jobsRepository=jobsRepository;
    }
    public List<JobsModel> getJobs(){
        return jobsRepository.getJobs();
    }
    public boolean addJob(String name, String startDate, String endDate){
        int result = jobsRepository.addJob(name,startDate,endDate);
        return result>0?true:false;
    }
    public boolean deleteJobById(int id){
        int result = jobsRepository.deleteJobById(id);
        return result>0?true:false;
    }

    public boolean updateJob(String name, String startDate, String endDate, int id) {
        int result = jobsRepository.updateJob(name, startDate, endDate, id);
        return result>0?true:false;
    }
    public JobsModel getJobById(int id){
        return jobsRepository.getJobById(id);
    }
    public List<QualitiesModel> getQuantitiesByJobId(int id){
        return  jobsRepository.getQuantitiesByJobId(id);
    }
    public List<QualitiesModel> getQuantitiesByUserId(int id) {
        return jobsRepository.getQuantitiesByUserId(id);
    }
    public List<JobsModel> getJobsByUserId(int id) {
        return jobsRepository.getJobsByUserId(id);
    }
}

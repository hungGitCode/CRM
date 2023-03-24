package com.cybersoft.crm.repository;

import com.cybersoft.crm.model.JobsModel;
import com.cybersoft.crm.model.QualitiesModel;

import java.util.List;

public interface JobsRepository {
    public List<JobsModel> getJobs();
    public int addJob(String name, String startDate, String endDate);
    public int deleteJobById(int id);
    public int updateJob(String name, String startDate, String endDate, int id);
    public JobsModel getJobById(int id);
    public List<QualitiesModel> getQuantitiesByJobId(int id);
    public List<QualitiesModel> getQuantitiesByUserId(int id);
    public List<JobsModel> getJobsByUserId(int id);
}

package backend.controller;

import backend.service.ICandidateService;
import backend.service.impl.CandidateService;
import entity.Candidate;
import entity.GraduationRank;

public class CandidateController {
    private ICandidateService candidateService;

    public CandidateController() {
        candidateService = new CandidateService();
    }

    public boolean registerExperience(String firstName, String lastName, String phone, String email, int expInYear, String proSkill, String password) {
        return candidateService.registerExperience(firstName, lastName, phone, email,expInYear, proSkill, password);
    }

    public boolean registerFresher(String firstName, String lastName, String phone, String email, GraduationRank graduationRank, String password) {
        return candidateService.registerFresher(firstName, lastName, phone, email, graduationRank, password);

    }

    public Candidate login(String email, String password) {
        return candidateService.login(email, password);
    }

    public boolean checkEmailExist(String email){
        return candidateService.checkEmailExist(email);
    }
}

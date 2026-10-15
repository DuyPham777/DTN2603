package backend.service.impl;

import backend.repository.ICandidateRepository;
import backend.repository.impl.CandidateRepository;
import backend.service.ICandidateService;
import entity.Candidate;
import entity.GraduationRank;

public class CandidateService implements ICandidateService {
    private ICandidateRepository candidateRepository;

    public CandidateService() {
        candidateRepository = new CandidateRepository();
    }

    @Override
    public boolean registerExperience(String firstName, String lastName, String phone, String email, int expInYear, String proSkill, String password) {
        return candidateRepository.registerExperience(firstName, lastName, phone, email,expInYear, proSkill, password);
    }

    @Override
    public boolean registerFresher(String firstName, String lastName, String phone, String email, GraduationRank graduationRank, String password) {
        return candidateRepository.registerFresher(firstName, lastName, phone, email, graduationRank, password);
    }

    @Override
    public Candidate login(String email, String password) {
        return candidateRepository.login(email, password);
    }

    @Override
    public boolean checkEmailExist(String email) {
        return candidateRepository.checkEmailExist(email);
    }
}

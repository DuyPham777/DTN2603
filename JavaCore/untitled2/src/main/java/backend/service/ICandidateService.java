package backend.service;

import entity.Candidate;
import entity.GraduationRank;

public interface ICandidateService {
    boolean registerExperience(String firstName, String lastName, String phone, String email, int expInYear, String proSkill, String password);
    boolean registerFresher(String firstName, String lastName, String phone, String email, GraduationRank graduationRank, String password);

    Candidate login(String email, String password);
    boolean checkEmailExist(String email);
}

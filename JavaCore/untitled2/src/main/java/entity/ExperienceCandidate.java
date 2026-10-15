package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ExperienceCandidate extends Candidate{
    private int expInYear;
    private String proSkill;

    public ExperienceCandidate(int id, String firstName, String lastName, String phone, String email, String password, Role role, int expInYear, String proSkill) {
        super(id, firstName, lastName, phone, email, password, role);
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    public ExperienceCandidate() {
        super();
    }
}





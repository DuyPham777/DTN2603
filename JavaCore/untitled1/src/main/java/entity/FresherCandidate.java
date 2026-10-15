package entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class FresherCandidate extends Candidate{
    private GraduationRank graduationRank;

    public FresherCandidate(int id, String firstName, String lastName, String phone, String email, String password, Role role, GraduationRank graduationRank) {
        super(id, firstName, lastName, phone, email, password, role);
        this.graduationRank = graduationRank;
    }

    public FresherCandidate() {
        super();
    }
}

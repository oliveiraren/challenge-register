package com.challenge.hmvfiap.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Entity
public class UserHealthData {

    @Id
    private Long userId;

    private Boolean heartAttack;

    private String heartAttackKinship;

    private Boolean obese;

    private Boolean medication;

    private String medicationWhich;

    private Boolean allergy;

    private String drugAllergy;

    private LocalDateTime modifiedOn;

    @PreUpdate
    protected void onUpdate() {
        modifiedOn = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHealthData that = (UserHealthData) o;
        return this.heartAttack == that.heartAttack &&
                this.heartAttackKinship.equals(that.heartAttackKinship) &&
                this.obese == that.obese &&
                this.medication == that.medication &&
                this.medicationWhich.equals(that.medicationWhich) &&
                this.allergy == that.allergy &&
                this.drugAllergy.equals(that.drugAllergy);
    }
}

package org.sleon.electronicHealthRecord.models;

public class Paciente extends Usuario{
    private UsuarioHospital personalAdmision;
    private String malestar;

    public String getMalestar() {
        return malestar;
    }

    public void setMalestar(String malestar) {
        this.malestar = malestar;
    }

    public UsuarioHospital getPersonalAdmision() {
        return personalAdmision;
    }

    public void setPersonalAdmision(UsuarioHospital personalAdmision) {
        this.personalAdmision = personalAdmision;
    }
}
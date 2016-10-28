package com.example.rapid.stickyrecyclerdemo2;

/**
 * Recycler Data
 */

public class Contact {


    private String name;
    private String phoneNo;
    private String email;
    private String inviteStatus;
    private boolean isSelected;

    public Contact(String name, String phoneNo, String email, String inviteStatus, boolean isSelected) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
        this.inviteStatus = inviteStatus;
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(String inviteStatus) {
        this.inviteStatus = inviteStatus;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

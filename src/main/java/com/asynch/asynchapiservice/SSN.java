package com.asynch.asynchapiservice;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Validated
public class SSN implements Serializable {

    //@Pattern(regexp = "^([0-9]{3}-[0-9]{2}-[0-9]{4})|([0-9]{9})|([0-9]{2}-[0-9]{7})$")
    @Pattern(regexp = "^(?!666|000|9\\d{2})\\d{3}(?!00)\\d{2}(?!0{4})\\d{4}$")
    @NotNull
    private String taxId;

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }
}

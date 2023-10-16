package com.happyworld.fooservice.dto;

import jakarta.validation.constraints.NotEmpty;

public class ElementDto
{

    @NotEmpty
    private String elementName;

    @NotEmpty
    private String elementDesc;

    public String getElementName()
    {
        return elementName;
    }

    public void setElementName( String elementName )
    {
        this.elementName = elementName;
    }

    public String getElementDesc()
    {
        return elementDesc;
    }

    public void setElementDesc( String elementDesc )
    {
        this.elementDesc = elementDesc;
    }
}

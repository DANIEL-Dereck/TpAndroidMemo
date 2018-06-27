package com.example.dereck.memo.Entity;

import java.time.LocalDateTime;

public class Memo {

    private Integer id;
    private String text;
    private Boolean state;

    //Constructor
    public Memo()
    {
        this.state = false;
    }

    public Memo(String text)
    {
        this.text = text;
        this.state = false;
    }

    public String getText()
    {
        return this.text;
    }

    public Memo setText(String text)
    {
        this.text = text;
        return this;
    }

    public Boolean getState()
    {
        return this.state;
    }

    public Memo setState(Boolean state)
    {
        this.state = state;
        return this;
    }

    public Memo setId(Integer id)
    {
        this.id = id;
        return this;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void stateChange()
    {
        this.state = !this.state;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

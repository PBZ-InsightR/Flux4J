package com.flux4j.scenarios;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;
import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

public class BasicStepDef implements En {
    List<String> animals = new ArrayList<>();

    @Given("the following animals: (.*)")
    public void the_following_animals(List<String> animals) {
        for (String animal : animals) {
            this.animals.add(animal);
            System.out.println("animal = " + animal);
        }
    }

    @When("I decide to eat the (.*)")
    public void i_decide_to_eat(String animal) {
        animals.remove(animal);
    }

    @Then("I am waiting ([^\"]*) ms")
    public void petite_pause(long millis) throws InterruptedException {
        System.out.println("pause de " + millis + " ms");
        Thread.sleep(millis);
        System.out.println("fin pause");
    }

    @Then("It remains only ([^\"]*) animals")
    public void it_remains(int expectedNumber) {
        Assert.assertEquals(expectedNumber, this.animals.size());
    }


}
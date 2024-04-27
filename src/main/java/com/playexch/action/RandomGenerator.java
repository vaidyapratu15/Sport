package com.playexch.action;

import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import com.github.javafaker.Faker;

public class RandomGenerator {
    
    
    
    public  String generateRandomPassword() {
        Faker faker = new Faker();
        String password = faker.regexify("[A-Z][a-z0-9@#$%]{7,11}");
        return password;
    }
    
    public String generateRandomUsername() {
        Faker faker = new Faker();
        String username = faker.regexify("[a-zA-Z]{8}");
        return username;
    }
    
    public String generateRandomName(int number) {
	    String name = RandomStringUtils.randomAlphabetic(number).toLowerCase();
	    return name;
	}
    
    public  String generateRandomNumber(int number) {
        Faker faker = new Faker();
        String mobileNumber = faker.number().digits(number);
        return mobileNumber;
    }
    
    public  String generateRandomStrongPassword() {
        Faker faker = new Faker();
        String password = faker.bothify("P???@###");
        return password;
    }
    
    public  String generateRandomWeakPassword() {
        Faker faker = new Faker();
        String password = faker.bothify("P?@##");
        return password;
    }

    public String generateRandomWords() {   //itaque pariatur similique pariatur dolor odit
	    Faker faker = new Faker();
	    List<String> words = faker.lorem().words(6);
	    String name = String.join(" ", words);
	    return name;
	}
    
    public String generateRandomName() {    //any no of letter
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        return firstName;
    }
	
    public String generateRandomSpacePassword() {
        Faker faker = new Faker();
        String spacePassword = faker.regexify("[A-Za-z]{3,5} [A-Za-z]{2,4}[ ]{1}[A-Za-z]{1,3}");
        return spacePassword;
    }
    
    public String generatePassword() {    // all time Password@ common only after 3 int diff
        Faker faker = new Faker();
        String suffix = faker.numerify("###"); // Generate a random three-digit number
        String password = "Password@123".replace("123", suffix);
        return password;
    }




}

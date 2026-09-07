package com.gamezone.service;

import com.gamezone.model.Client;
import com.gamezone.model.Person;
import com.gamezone.model.Seller;
import com.gamezone.persistence.PersonRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonService {

    private final PersonRepository personRepository;
    private final List
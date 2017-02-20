package com.epam.task05.service;

import com.epam.task05.bean.Dish;
import com.epam.task05.service.exception.MenuServiceException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public interface MenuService {

    HashMap<String, ArrayList<Dish>> readFile(String filePath, String parser) throws MenuServiceException;
}

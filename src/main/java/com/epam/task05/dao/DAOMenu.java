package com.epam.task05.dao;

import com.epam.task05.bean.Dish;
import com.epam.task05.dao.exception.DAOException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yauheni_Borbut on 2/20/2017.
 */
public interface DAOMenu {

    HashMap<String, ArrayList<Dish>> read(String filePath, String parser) throws DAOException;
}

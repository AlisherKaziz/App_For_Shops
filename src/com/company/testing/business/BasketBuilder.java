package com.company.testing.business;

import java.sql.*;
import java.util.LinkedList;

public interface BasketBuilder<T> extends getContact{
    void push(T element);
    void remove(T element);
    void printBasket();
    void makeEmpty(String choice);
}

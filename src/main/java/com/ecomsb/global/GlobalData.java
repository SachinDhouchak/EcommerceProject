package com.ecomsb.global;

import java.util.ArrayList;
import java.util.List;

import com.ecomsb.model.Product;

public class GlobalData {

	public static List<Product> cart;
	
	static {
		cart = new ArrayList<Product>();
	}
}

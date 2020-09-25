package com.codeit.rajat.movys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author rajat
 *
 * Landing page 
 *
 */

@Controller
public class LandingController
{
	@RequestMapping("")
	public String index()
	{
		return "index.html";
	}

}

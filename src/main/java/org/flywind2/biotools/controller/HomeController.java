/**
 * 
 */
package org.flywind2.biotools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author SuFeng
 *
 */
@Controller(value="/")
public class HomeController {
	@RequestMapping(value={"/","index.html","home.html"},method=RequestMethod.GET)
	public String home(){
		return "index";
	}
}

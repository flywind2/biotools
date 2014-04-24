/**
 * 
 */
package org.flywind2.biotools.controller.gene;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.flywind2.biotools.model.gene.Gene;
import org.flywind2.biotools.repository.GeneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author SuFeng
 * 
 */
@Controller
@RequestMapping(value = { "/gene/*", "/gene" })
public class GeneController {

	@Autowired
	private GeneRepository geneRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String gene(Model model) {
		model.addAttribute("gene", new Gene());
		return "gene/gene";
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public String query(
			@RequestParam(required = true, value = "field") String field,
			@RequestParam(value = "value") String value,
			@RequestParam("file") MultipartFile file, Model model)
			throws IOException {

		List<String> symbols = new ArrayList<>();

		if (value != null) {
			BufferedReader br = new BufferedReader(new StringReader(value));
			String line = null;
			while ((line = br.readLine()) != null) {
				symbols.add(line.trim());
			}
		}

		if (!file.isEmpty()) {
			System.out.println(file.getOriginalFilename());
			BufferedReader br = new BufferedReader(new InputStreamReader(
					file.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				symbols.add(line.trim());
			}
		}

		model.addAttribute("field", field);
		if (field.equalsIgnoreCase("symbol")) {
			// List<Gene> list = geneRepository.findByProperty(new
			// String[]{value});
			List<Gene> list = geneRepository.findBySymbolIn(symbols);
			System.out.println("get search result is " + list.size());
			model.addAttribute("genes", list);

		} else if (field.equalsIgnoreCase("geneid")) {
			List<Gene> list = geneRepository.findByGeneIDIn(symbols);
			System.out.println("get search result is " + list.size());
			model.addAttribute("genes", list);
		}
		return "gene/gene";
	}

	@RequestMapping(value = "queryAsyn", method = RequestMethod.POST)
	@ResponseBody
	public DeferredResult<List<Gene>> asyn(
			@RequestParam(required = true, value = "field") String field,
			@RequestParam(value = "value") String value,
			@RequestParam("file") MultipartFile file, Model model)
			throws IOException {
		DeferredResult<List<Gene>> dr = new DeferredResult<List<Gene>>(null,
				Collections.emptyList());

		List<String> symbols = new ArrayList<>();

		if (value != null) {
			BufferedReader br = new BufferedReader(new StringReader(value));
			String line = null;
			while ((line = br.readLine()) != null) {
				symbols.add(line.trim());
			}
		}

		if (!file.isEmpty()) {
			System.out.println(file.getOriginalFilename());
			BufferedReader br = new BufferedReader(new InputStreamReader(
					file.getInputStream()));
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				symbols.add(line.trim());
			}
		}

		model.addAttribute("field", field);
		if (field.equalsIgnoreCase("symbol")) {
			// List<Gene> list = geneRepository.findByProperty(new
			// String[]{value});
			List<Gene> list = geneRepository.findBySymbolIn(symbols);
			dr.setResult(list);

		} else if (field.equalsIgnoreCase("geneid")) {
			List<Gene> list = geneRepository.findByGeneIDIn(symbols);
			dr.setResult(list);
		}

		return dr;
	}

}

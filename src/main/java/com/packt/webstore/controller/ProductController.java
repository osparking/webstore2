package com.packt.webstore.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.CategoryNoProduct;
import com.packt.webstore.exception.NoCategoryException;
import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.service.ProductService;
import com.packt.webstore.validator.ProductValidator;
import com.packt.webstore.validator.UnitsInStockValidator;

@Controller
@RequestMapping("market")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductValidator productValidator;
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("productId",
				"name",
				"unitPrice",
				"description",
				"manufacturer",
				"category",
				"delivery",
				"unitsInStock",
				"condition", 
				"productImage",
				"pdfManual");
		binder.setValidator(productValidator);
	}
	@ExceptionHandler(CategoryNoProduct.class)
	public ModelAndView handleCategoryNoProduct(HttpServletRequest req, 
			CategoryNoProduct exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("emptyCategory", exception.getCategory());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL() 
				+ "?" + req.getQueryString());
		mav.setViewName("categoryNotFound");
		return mav;
	}	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req, 
			ProductNotFoundException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductId", exception.getProductId());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL() 
				+ "?" + req.getQueryString());
		mav.setViewName("productNotFound");
		return mav;
	}	
	
	@RequestMapping("/products/invalidPromoCode") 
	public String invalidPromoCode() {
		return "invalidPromoCode";
	}
	
	@RequestMapping(value="/products/add", method=RequestMethod.GET)
	public String getAddNewProductFrom(
			@ModelAttribute("newProduct") Product newProduct) {
		return "addProduct";
	}
	
	@RequestMapping(value="/products/add", method=RequestMethod.POST)
	public String processAddNewProductFrom(
			@ModelAttribute("newProduct") @Valid Product newProduct,
			BindingResult result, HttpServletRequest request) {
		
		if (result.hasErrors()) {
			return "addProduct";
		}
		
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException(
					"Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		/**
		* 상품 영상 메모리 내용 정한 폴더에 파일로 보관
		*/
		MultipartFile productImage = newProduct.getProductImage();
		String rootDirectory = 
				request.getSession().getServletContext().getRealPath("/");
		if (productImage != null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(rootDirectory 
						+ "resources\\images\\" + newProduct.getProductId() 
						+ ".png"));
			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}

		/**
		* 상품 메뉴얼 pdf 파일 정한 폴더에 파일로 보관
		*/
		MultipartFile pdfManual = newProduct.getPdfManual();
		if (pdfManual != null && !pdfManual.isEmpty()) {
			try {
				pdfManual.transferTo(new File(rootDirectory 
						+ "resources\\pdf\\" + newProduct.getProductId() 
						+ ".pdf"));
			} catch (Exception e) {
				throw new RuntimeException("Pdf Manual saving failed", e);
			}
		}

		productService.addProduct(newProduct);
		return "redirect:/market/products";
	}

	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
		productService.updateAllStock();
		return "redirect:/market/products";
	}

	@RequestMapping("/products")
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

//	@RequestMapping("/products/filter/{params}/{specif}")
//	public String listByFilter(Model model, 
//			@MatrixVariable(pathVar = "params") Map<String, List<String>> params,
//			@MatrixVariable(pathVar = "specif") Map<String, List<String>> specif) {
//		model.addAttribute("products", productService.getByFilter(params));
//		return "products";
//	}
	
//	@RequestMapping("/products/{category}/{specif}")
//	public String listByCateSpec(Model model, 
//			@PathVariable("category") String category,
//			@MatrixVariable(pathVar = "specif") Map<String, List<String>> specif,
//			@RequestParam String brand) {
//		model.addAttribute("products", 
//				productService.listByCateSpec(category, specif, brand));
//		model.addAttribute("method", "pathvariable");
//		return "products";
//	}
	
	@RequestMapping("/products/{category}")
	public String listByCategory(Model model, 
			@PathVariable("category") String category) {
		List<Product> products 
			= productService.getByCategory(category);
		
		if (products == null || products.isEmpty()) {
			throw new NoCategoryException();
		}
		model.addAttribute("products", products);
		model.addAttribute("method", "pathvariable");
		return "products";
	}
	
	@RequestMapping("/product")
	public String productById(Model model, @RequestParam String id, 
			HttpServletRequest request) {
		
		System.out.println("request.getContextPath(): " + 
				request.getContextPath());
		System.out.println("request.getRequestURI(): " + 
				request.getRequestURI());
		System.out.println("request.getRequestURL(): " + 
				request.getRequestURL());
		
		model.addAttribute("product", productService.getById(id));
		model.addAttribute("id", id);
		return "product";
	}

	/**
	 * 비효율적인 버전 -- 연습용
	 * 
	 * @param model
	 * @return
	 */
//	@RequestMapping("/products/laptop")
//	public String laptop(Model model) {
//		model.addAttribute("products", productService.getAllProducts("laptop"));
//		return "products";
//	}
//
//	@RequestMapping("/products/tablet")
//	public String tablet(Model model) {
//		model.addAttribute("products", productService.getAllProducts("tablet"));
//		model.addAttribute("method", "ineffi");
//		return "products";
//	}
}

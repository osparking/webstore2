package com.packt.webstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import com.packt.webstore.validator.ProductCategory;
import com.packt.webstore.validator.ProductId;

@XmlRootElement
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Pattern(regexp="P[0-9]+", message="{Pattern.Product.productId.validation}")
	@ProductId
	private String productId; //
	@Size(min=4, max=50, message="{Size.Product.name.validation}")
	private String name; //
	
	@Min(value=0, message="{Min.Product.unitPrice.validation}")
	@Digits(integer=10, fraction=0,
	message="{Digits.Product.unitPrice.validation}")
	@NotNull(message="{NotNull.Product.unitPrice.validation}")
	private BigDecimal unitPrice; //
	
	private String unitPriceComma; //
	private String description;
	private String manufacturer;
	@Pattern(regexp="[a-z|A-Z|?? -?? |?? -?? |??-?R]+", message="{Pattern.Product.category.validation}")
	@NotNull(message="{NotNull.Product.category.validation}")
	@ProductCategory
	private String category;
	
	@NotNull(message="{NotNull.Product.unitsInStock.validation}")
	/*
	 * @Digits(integer=8, fraction=0,
	 * message="{Digits.Product.unitsInStock.validation}")
	 */
	@Min(value=0, message="{Min.Product.unitsInStock.validation}")
	private long unitsInStock;
	private String unitsInStockComma;
	private long unitsInOrder;
	private boolean discontinued;
	private String condition;
	@JsonIgnore
	private MultipartFile productImage;
	@JsonIgnore
	private MultipartFile pdfManual;

	public Product() {
		super();
	}

	public Product(String productId, String name, int unitPrice) {
		this.productId = productId;
		this.name = name;
		this.unitPrice = new BigDecimal(unitPrice);
	}

	@XmlTransient
	public MultipartFile getPdfManual() {
		return pdfManual;
	}

	public void setPdfManual(MultipartFile pdfManual) {
		this.pdfManual = pdfManual;
	}

	@XmlTransient
	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	public String getUnitsInStockComma() {
		return unitsInStockComma;
	}

	public void setUnitsInStockComma(String unitsInStockComma) {
		this.unitsInStockComma = unitsInStockComma;
	}

	public String getUnitPriceComma() {
		return unitPriceComma;
	}

	public void setUnitPriceComma(String unitPriceComma) {
		this.unitPriceComma = unitPriceComma;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public long getUnitsInOrder() {
		return unitsInOrder;
	}

	public void setUnitsInOrder(long unitsInOrder) {
		this.unitsInOrder = unitsInOrder;
	}

	public boolean isDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result 
				+ ((productId == null) ? 0 : productId.hashCode());
		return result;
	}
}

package org.flywind2.biotools.model.gene;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the gene database table.
 * 
 */
@Entity
@Table(name="gene")
@NamedQuery(name="Gene.findAll", query="SELECT g FROM Gene g")
public class Gene implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true,nullable=false)
	private Long id;

	@Column(length=255)
	private String chromosome;

	@Column(length=255)
	private String dbXrefs;

	@Column(length=2000)
	private String description;

	@Column(length=500)
	private String full_name_from_nomenclature_authority;

	@Column(length=255)
	private String geneID;

	@Column(length=255)
	private String locusTag;

	@Column(name="map_location", length=255)
	private String mapLocation;

	@Column(length=255)
	private String modification_date;

	@Column(length=255)
	private String nomenclature_status;

	@Column(length=4000)
	private String other_designations;

	@Column(length=255)
	private String symbol;

	@Column(length=500)
	private String symbol_from_nomenclature_authority;

	@Column(length=255)
	private String synonyms;

	@Column(name="tax_id", length=255)
	private String taxId;

	@Column(name="type_of_gene", length=255)
	private String typeOfGene;

	public Gene() {
	}

	public String getChromosome() {
		return this.chromosome;
	}

	public void setChromosome(String chromosome) {
		this.chromosome = chromosome;
	}

	public String getDbXrefs() {
		return this.dbXrefs;
	}

	public void setDbXrefs(String dbXrefs) {
		this.dbXrefs = dbXrefs;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFull_name_from_nomenclature_authority() {
		return this.full_name_from_nomenclature_authority;
	}

	public void setFull_name_from_nomenclature_authority(String full_name_from_nomenclature_authority) {
		this.full_name_from_nomenclature_authority = full_name_from_nomenclature_authority;
	}

	public String getGeneID() {
		return this.geneID;
	}

	public void setGeneID(String geneID) {
		this.geneID = geneID;
	}

	public String getLocusTag() {
		return this.locusTag;
	}

	public void setLocusTag(String locusTag) {
		this.locusTag = locusTag;
	}

	public String getMapLocation() {
		return this.mapLocation;
	}

	public void setMapLocation(String mapLocation) {
		this.mapLocation = mapLocation;
	}

	public String getModification_date() {
		return this.modification_date;
	}

	public void setModification_date(String modification_date) {
		this.modification_date = modification_date;
	}

	public String getNomenclature_status() {
		return this.nomenclature_status;
	}

	public void setNomenclature_status(String nomenclature_status) {
		this.nomenclature_status = nomenclature_status;
	}

	public String getOther_designations() {
		return this.other_designations;
	}

	public void setOther_designations(String other_designations) {
		this.other_designations = other_designations;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol_from_nomenclature_authority() {
		return this.symbol_from_nomenclature_authority;
	}

	public void setSymbol_from_nomenclature_authority(String symbol_from_nomenclature_authority) {
		this.symbol_from_nomenclature_authority = symbol_from_nomenclature_authority;
	}

	public String getSynonyms() {
		return this.synonyms;
	}

	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}

	public String getTaxId() {
		return this.taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getTypeOfGene() {
		return this.typeOfGene;
	}

	public void setTypeOfGene(String typeOfGene) {
		this.typeOfGene = typeOfGene;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
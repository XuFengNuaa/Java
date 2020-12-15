package nuaa.casting.service;

public class MaterialProperties {
	private String materialcategory;
	private String materialbrand;
	private String tensilestrength;
	private String extensionstrength;
	private String elongation;
	private String hardness;
	private String mainmatrixorganization;
	
	private String distributionratio;
	private String melting;
	private String inoculation;
	private String spheroidizing;
	private String annealing;
	
	public String getMaterialcategory() {
		return materialcategory;
	}
	public void setMaterialcategory(String materialcategory) {
		this.materialcategory = materialcategory;
	}
	public String getMaterialbrand() {
		return materialbrand;
	}
	public void setMaterialbrand(String materialbrand) {
		this.materialbrand = materialbrand;
	}
	public String getTensilestrength() {
		return tensilestrength;
	}
	public void setTensilestrength(String tensilestrength) {
		this.tensilestrength = tensilestrength;
	}
	public String getExtensionstrength() {
		return extensionstrength;
	}
	public void setExtensionstrength(String extensionstrength) {
		this.extensionstrength = extensionstrength;
	}
	public String getElongation() {
		return elongation;
	}
	public void setElongation(String elongation) {
		this.elongation = elongation;
	}
	public String getHardness() {
		return hardness;
	}
	public void setHardness(String hardness) {
		this.hardness = hardness;
	}
	public String getMainmatrixorganization() {
		return mainmatrixorganization;
	}
	public void setMainmatrixorganization(String mainmatrixorganization) {
		this.mainmatrixorganization = mainmatrixorganization;
	}
	
	
	
	
	public String getDistributionratio() {
		return distributionratio;
	}
	public void setDistributionratio(String distributionratio) {
		this.distributionratio = distributionratio;
	}
	public String getMelting() {
		return melting;
	}
	public void setMelting(String melting) {
		this.melting = melting;
	}
	public String getInoculation() {
		return inoculation;
	}
	public void setInoculation(String inoculation) {
		this.inoculation = inoculation;
	}
	public String getSpheroidizing() {
		return spheroidizing;
	}
	public void setSpheroidizing(String spheroidizing) {
		this.spheroidizing = spheroidizing;
	}
	public String getAnnealing() {
		return annealing;
	}
	public void setAnnealing(String annealing) {
		this.annealing = annealing;
	}
	@Override
	public String toString() {
		return "MaterialProperties [materialcategory=" + materialcategory + ", materialbrand=" + materialbrand
				+ ", tensilestrength=" + tensilestrength + ", extensionstrength=" + extensionstrength + ", elongation="
				+ elongation + ", hardness=" + hardness + ", mainmatrixorganization=" + mainmatrixorganization
				+ ", distributionratio=" + distributionratio + ", melting=" + melting + ", inoculation=" + inoculation
				+ ", spheroidizing=" + spheroidizing + ", annealing=" + annealing + "]";
	}
	
	
}

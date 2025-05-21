package com.ssafy.nomnom.model.dto.meal;

public class Food {
	private String foodCode;
    private String foodName;
    private String foodUpperGroup;
    private String foodSubGroup;
    private double foodWeight;

    private double nutriEnergy;
    private double nutriWater;
    private double nutriProtein;
    private double nutriNitrogen;
    private double nutriAsh;
    private double nutriCarbohydrate;
    private double nutriSugar;
    private double nutriDietaryFiber;

    private double nutriVitaminACarotene;
    private double nutriVitaminARetinol;
    private double nutriNiacinEq;

    private double nutriTotalFattyAcids;
    private double nutriEssentialFattyAcids;
    private double nutriSaturatedFats;
    private double nutriMonounsaturatedFats;
    private double nutriPolyunsaturatedFats;

    private double nutriCalcium;
    private double nutriIron;
    private double nutriMagnesium;
    private double nutriPhosphorus;
    private double nutriPotassium;
    private double nutriSodium;

    private double nutriZinc;
    private double nutriCopper;
    private double nutriManganese;
    private double nutriSelenium;
    private double nutriMolybdenum;
    private double nutriIodine;

    private double nutriRetinol;
    private double nutriCarotene;

    private double nutriVitaminD;
    private double nutriVitaminE;
    private double nutriVitaminK1;

    private double nutriVitaminB1;
    private double nutriVitaminB2;
    private double nutriNiacin;
    private double nutriPantothenicAcid;
    private double nutriPyridoxine;
    private double nutriBiotin;
    private double nutriFolate;
    private double nutriVitaminB12;
    private double nutriVitaminC;

    private double nutriCholesterol;
    private double nutriSaltEquivalent;
    private double nutriRefuse;
    
	public Food() {
	}
	
	public Food(String foodCode, String foodName, String foodUpperGroup, String foodSubGroup, double foodWeight,
			double nutriEnergy, double nutriWater, double nutriProtein, double nutriNitrogen, double nutriAsh,
			double nutriCarbohydrate, double nutriSugar, double nutriDietaryFiber, double nutriVitaminACarotene,
			double nutriVitaminARetinol, double nutriNiacinEq, double nutriTotalFattyAcids,
			double nutriEssentialFattyAcids, double nutriSaturatedFats, double nutriMonounsaturatedFats,
			double nutriPolyunsaturatedFats, double nutriCalcium, double nutriIron, double nutriMagnesium,
			double nutriPhosphorus, double nutriPotassium, double nutriSodium, double nutriZinc, double nutriCopper,
			double nutriManganese, double nutriSelenium, double nutriMolybdenum, double nutriIodine,
			double nutriRetinol, double nutriCarotene, double nutriVitaminD, double nutriVitaminE,
			double nutriVitaminK1, double nutriVitaminB1, double nutriVitaminB2, double nutriNiacin,
			double nutriPantothenicAcid, double nutriPyridoxine, double nutriBiotin, double nutriFolate,
			double nutriVitaminB12, double nutriVitaminC, double nutriCholesterol, double nutriSaltEquivalent,
			double nutriRefuse) {
		super();
		this.foodCode = foodCode;
		this.foodName = foodName;
		this.foodUpperGroup = foodUpperGroup;
		this.foodSubGroup = foodSubGroup;
		this.foodWeight = foodWeight;
		this.nutriEnergy = nutriEnergy;
		this.nutriWater = nutriWater;
		this.nutriProtein = nutriProtein;
		this.nutriNitrogen = nutriNitrogen;
		this.nutriAsh = nutriAsh;
		this.nutriCarbohydrate = nutriCarbohydrate;
		this.nutriSugar = nutriSugar;
		this.nutriDietaryFiber = nutriDietaryFiber;
		this.nutriVitaminACarotene = nutriVitaminACarotene;
		this.nutriVitaminARetinol = nutriVitaminARetinol;
		this.nutriNiacinEq = nutriNiacinEq;
		this.nutriTotalFattyAcids = nutriTotalFattyAcids;
		this.nutriEssentialFattyAcids = nutriEssentialFattyAcids;
		this.nutriSaturatedFats = nutriSaturatedFats;
		this.nutriMonounsaturatedFats = nutriMonounsaturatedFats;
		this.nutriPolyunsaturatedFats = nutriPolyunsaturatedFats;
		this.nutriCalcium = nutriCalcium;
		this.nutriIron = nutriIron;
		this.nutriMagnesium = nutriMagnesium;
		this.nutriPhosphorus = nutriPhosphorus;
		this.nutriPotassium = nutriPotassium;
		this.nutriSodium = nutriSodium;
		this.nutriZinc = nutriZinc;
		this.nutriCopper = nutriCopper;
		this.nutriManganese = nutriManganese;
		this.nutriSelenium = nutriSelenium;
		this.nutriMolybdenum = nutriMolybdenum;
		this.nutriIodine = nutriIodine;
		this.nutriRetinol = nutriRetinol;
		this.nutriCarotene = nutriCarotene;
		this.nutriVitaminD = nutriVitaminD;
		this.nutriVitaminE = nutriVitaminE;
		this.nutriVitaminK1 = nutriVitaminK1;
		this.nutriVitaminB1 = nutriVitaminB1;
		this.nutriVitaminB2 = nutriVitaminB2;
		this.nutriNiacin = nutriNiacin;
		this.nutriPantothenicAcid = nutriPantothenicAcid;
		this.nutriPyridoxine = nutriPyridoxine;
		this.nutriBiotin = nutriBiotin;
		this.nutriFolate = nutriFolate;
		this.nutriVitaminB12 = nutriVitaminB12;
		this.nutriVitaminC = nutriVitaminC;
		this.nutriCholesterol = nutriCholesterol;
		this.nutriSaltEquivalent = nutriSaltEquivalent;
		this.nutriRefuse = nutriRefuse;
	}
	public String getFoodCode() {
		return foodCode;
	}
	public void setFoodCode(String foodCode) {
		this.foodCode = foodCode;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getFoodUpperGroup() {
		return foodUpperGroup;
	}
	public void setFoodUpperGroup(String foodUpperGroup) {
		this.foodUpperGroup = foodUpperGroup;
	}
	public String getFoodSubGroup() {
		return foodSubGroup;
	}
	public void setFoodSubGroup(String foodSubGroup) {
		this.foodSubGroup = foodSubGroup;
	}
	public double getFoodWeight() {
		return foodWeight;
	}
	public void setFoodWeight(double foodWeight) {
		this.foodWeight = foodWeight;
	}
	public double getNutriEnergy() {
		return nutriEnergy;
	}
	public void setNutriEnergy(double nutriEnergy) {
		this.nutriEnergy = nutriEnergy;
	}
	public double getNutriWater() {
		return nutriWater;
	}
	public void setNutriWater(double nutriWater) {
		this.nutriWater = nutriWater;
	}
	public double getNutriProtein() {
		return nutriProtein;
	}
	public void setNutriProtein(double nutriProtein) {
		this.nutriProtein = nutriProtein;
	}
	public double getNutriNitrogen() {
		return nutriNitrogen;
	}
	public void setNutriNitrogen(double nutriNitrogen) {
		this.nutriNitrogen = nutriNitrogen;
	}
	public double getNutriAsh() {
		return nutriAsh;
	}
	public void setNutriAsh(double nutriAsh) {
		this.nutriAsh = nutriAsh;
	}
	public double getNutriCarbohydrate() {
		return nutriCarbohydrate;
	}
	public void setNutriCarbohydrate(double nutriCarbohydrate) {
		this.nutriCarbohydrate = nutriCarbohydrate;
	}
	public double getNutriSugar() {
		return nutriSugar;
	}
	public void setNutriSugar(double nutriSugar) {
		this.nutriSugar = nutriSugar;
	}
	public double getNutriDietaryFiber() {
		return nutriDietaryFiber;
	}
	public void setNutriDietaryFiber(double nutriDietaryFiber) {
		this.nutriDietaryFiber = nutriDietaryFiber;
	}
	public double getNutriVitaminACarotene() {
		return nutriVitaminACarotene;
	}
	public void setNutriVitaminACarotene(double nutriVitaminACarotene) {
		this.nutriVitaminACarotene = nutriVitaminACarotene;
	}
	public double getNutriVitaminARetinol() {
		return nutriVitaminARetinol;
	}
	public void setNutriVitaminARetinol(double nutriVitaminARetinol) {
		this.nutriVitaminARetinol = nutriVitaminARetinol;
	}
	public double getNutriNiacinEq() {
		return nutriNiacinEq;
	}
	public void setNutriNiacinEq(double nutriNiacinEq) {
		this.nutriNiacinEq = nutriNiacinEq;
	}
	public double getNutriTotalFattyAcids() {
		return nutriTotalFattyAcids;
	}
	public void setNutriTotalFattyAcids(double nutriTotalFattyAcids) {
		this.nutriTotalFattyAcids = nutriTotalFattyAcids;
	}
	public double getNutriEssentialFattyAcids() {
		return nutriEssentialFattyAcids;
	}
	public void setNutriEssentialFattyAcids(double nutriEssentialFattyAcids) {
		this.nutriEssentialFattyAcids = nutriEssentialFattyAcids;
	}
	public double getNutriSaturatedFats() {
		return nutriSaturatedFats;
	}
	public void setNutriSaturatedFats(double nutriSaturatedFats) {
		this.nutriSaturatedFats = nutriSaturatedFats;
	}
	public double getNutriMonounsaturatedFats() {
		return nutriMonounsaturatedFats;
	}
	public void setNutriMonounsaturatedFats(double nutriMonounsaturatedFats) {
		this.nutriMonounsaturatedFats = nutriMonounsaturatedFats;
	}
	public double getNutriPolyunsaturatedFats() {
		return nutriPolyunsaturatedFats;
	}
	public void setNutriPolyunsaturatedFats(double nutriPolyunsaturatedFats) {
		this.nutriPolyunsaturatedFats = nutriPolyunsaturatedFats;
	}
	public double getNutriCalcium() {
		return nutriCalcium;
	}
	public void setNutriCalcium(double nutriCalcium) {
		this.nutriCalcium = nutriCalcium;
	}
	public double getNutriIron() {
		return nutriIron;
	}
	public void setNutriIron(double nutriIron) {
		this.nutriIron = nutriIron;
	}
	public double getNutriMagnesium() {
		return nutriMagnesium;
	}
	public void setNutriMagnesium(double nutriMagnesium) {
		this.nutriMagnesium = nutriMagnesium;
	}
	public double getNutriPhosphorus() {
		return nutriPhosphorus;
	}
	public void setNutriPhosphorus(double nutriPhosphorus) {
		this.nutriPhosphorus = nutriPhosphorus;
	}
	public double getNutriPotassium() {
		return nutriPotassium;
	}
	public void setNutriPotassium(double nutriPotassium) {
		this.nutriPotassium = nutriPotassium;
	}
	public double getNutriSodium() {
		return nutriSodium;
	}
	public void setNutriSodium(double nutriSodium) {
		this.nutriSodium = nutriSodium;
	}
	public double getNutriZinc() {
		return nutriZinc;
	}
	public void setNutriZinc(double nutriZinc) {
		this.nutriZinc = nutriZinc;
	}
	public double getNutriCopper() {
		return nutriCopper;
	}
	public void setNutriCopper(double nutriCopper) {
		this.nutriCopper = nutriCopper;
	}
	public double getNutriManganese() {
		return nutriManganese;
	}
	public void setNutriManganese(double nutriManganese) {
		this.nutriManganese = nutriManganese;
	}
	public double getNutriSelenium() {
		return nutriSelenium;
	}
	public void setNutriSelenium(double nutriSelenium) {
		this.nutriSelenium = nutriSelenium;
	}
	public double getNutriMolybdenum() {
		return nutriMolybdenum;
	}
	public void setNutriMolybdenum(double nutriMolybdenum) {
		this.nutriMolybdenum = nutriMolybdenum;
	}
	public double getNutriIodine() {
		return nutriIodine;
	}
	public void setNutriIodine(double nutriIodine) {
		this.nutriIodine = nutriIodine;
	}
	public double getNutriRetinol() {
		return nutriRetinol;
	}
	public void setNutriRetinol(double nutriRetinol) {
		this.nutriRetinol = nutriRetinol;
	}
	public double getNutriCarotene() {
		return nutriCarotene;
	}
	public void setNutriCarotene(double nutriCarotene) {
		this.nutriCarotene = nutriCarotene;
	}
	public double getNutriVitaminD() {
		return nutriVitaminD;
	}
	public void setNutriVitaminD(double nutriVitaminD) {
		this.nutriVitaminD = nutriVitaminD;
	}
	public double getNutriVitaminE() {
		return nutriVitaminE;
	}
	public void setNutriVitaminE(double nutriVitaminE) {
		this.nutriVitaminE = nutriVitaminE;
	}
	public double getNutriVitaminK1() {
		return nutriVitaminK1;
	}
	public void setNutriVitaminK1(double nutriVitaminK1) {
		this.nutriVitaminK1 = nutriVitaminK1;
	}
	public double getNutriVitaminB1() {
		return nutriVitaminB1;
	}
	public void setNutriVitaminB1(double nutriVitaminB1) {
		this.nutriVitaminB1 = nutriVitaminB1;
	}
	public double getNutriVitaminB2() {
		return nutriVitaminB2;
	}
	public void setNutriVitaminB2(double nutriVitaminB2) {
		this.nutriVitaminB2 = nutriVitaminB2;
	}
	public double getNutriNiacin() {
		return nutriNiacin;
	}
	public void setNutriNiacin(double nutriNiacin) {
		this.nutriNiacin = nutriNiacin;
	}
	public double getNutriPantothenicAcid() {
		return nutriPantothenicAcid;
	}
	public void setNutriPantothenicAcid(double nutriPantothenicAcid) {
		this.nutriPantothenicAcid = nutriPantothenicAcid;
	}
	public double getNutriPyridoxine() {
		return nutriPyridoxine;
	}
	public void setNutriPyridoxine(double nutriPyridoxine) {
		this.nutriPyridoxine = nutriPyridoxine;
	}
	public double getNutriBiotin() {
		return nutriBiotin;
	}
	public void setNutriBiotin(double nutriBiotin) {
		this.nutriBiotin = nutriBiotin;
	}
	public double getNutriFolate() {
		return nutriFolate;
	}
	public void setNutriFolate(double nutriFolate) {
		this.nutriFolate = nutriFolate;
	}
	public double getNutriVitaminB12() {
		return nutriVitaminB12;
	}
	public void setNutriVitaminB12(double nutriVitaminB12) {
		this.nutriVitaminB12 = nutriVitaminB12;
	}
	public double getNutriVitaminC() {
		return nutriVitaminC;
	}
	public void setNutriVitaminC(double nutriVitaminC) {
		this.nutriVitaminC = nutriVitaminC;
	}
	public double getNutriCholesterol() {
		return nutriCholesterol;
	}
	public void setNutriCholesterol(double nutriCholesterol) {
		this.nutriCholesterol = nutriCholesterol;
	}
	public double getNutriSaltEquivalent() {
		return nutriSaltEquivalent;
	}
	public void setNutriSaltEquivalent(double nutriSaltEquivalent) {
		this.nutriSaltEquivalent = nutriSaltEquivalent;
	}
	public double getNutriRefuse() {
		return nutriRefuse;
	}
	public void setNutriRefuse(double nutriRefuse) {
		this.nutriRefuse = nutriRefuse;
	}
	@Override
	public String toString() {
		return "Food [foodCode=" + foodCode + ", foodName=" + foodName + ", foodUpperGroup=" + foodUpperGroup
				+ ", foodSubGroup=" + foodSubGroup + ", foodWeight=" + foodWeight + ", nutriEnergy=" + nutriEnergy
				+ ", nutriWater=" + nutriWater + ", nutriProtein=" + nutriProtein + ", nutriNitrogen=" + nutriNitrogen
				+ ", nutriAsh=" + nutriAsh + ", nutriCarbohydrate=" + nutriCarbohydrate + ", nutriSugar=" + nutriSugar
				+ ", nutriDietaryFiber=" + nutriDietaryFiber + ", nutriVitaminACarotene=" + nutriVitaminACarotene
				+ ", nutriVitaminARetinol=" + nutriVitaminARetinol + ", nutriNiacinEq=" + nutriNiacinEq
				+ ", nutriTotalFattyAcids=" + nutriTotalFattyAcids + ", nutriEssentialFattyAcids="
				+ nutriEssentialFattyAcids + ", nutriSaturatedFats=" + nutriSaturatedFats
				+ ", nutriMonounsaturatedFats=" + nutriMonounsaturatedFats + ", nutriPolyunsaturatedFats="
				+ nutriPolyunsaturatedFats + ", nutriCalcium=" + nutriCalcium + ", nutriIron=" + nutriIron
				+ ", nutriMagnesium=" + nutriMagnesium + ", nutriPhosphorus=" + nutriPhosphorus + ", nutriPotassium="
				+ nutriPotassium + ", nutriSodium=" + nutriSodium + ", nutriZinc=" + nutriZinc + ", nutriCopper="
				+ nutriCopper + ", nutriManganese=" + nutriManganese + ", nutriSelenium=" + nutriSelenium
				+ ", nutriMolybdenum=" + nutriMolybdenum + ", nutriIodine=" + nutriIodine + ", nutriRetinol="
				+ nutriRetinol + ", nutriCarotene=" + nutriCarotene + ", nutriVitaminD=" + nutriVitaminD
				+ ", nutriVitaminE=" + nutriVitaminE + ", nutriVitaminK1=" + nutriVitaminK1 + ", nutriVitaminB1="
				+ nutriVitaminB1 + ", nutriVitaminB2=" + nutriVitaminB2 + ", nutriNiacin=" + nutriNiacin
				+ ", nutriPantothenicAcid=" + nutriPantothenicAcid + ", nutriPyridoxine=" + nutriPyridoxine
				+ ", nutriBiotin=" + nutriBiotin + ", nutriFolate=" + nutriFolate + ", nutriVitaminB12="
				+ nutriVitaminB12 + ", nutriVitaminC=" + nutriVitaminC + ", nutriCholesterol=" + nutriCholesterol
				+ ", nutriSaltEquivalent=" + nutriSaltEquivalent + ", nutriRefuse=" + nutriRefuse + "]";
	}
    
    
}

package model;

public interface ImageModelV3 extends ImageModelV2{

  void applyPartialImageManipulation(String operationName,String imageName, String maskImageName, String sourceImageName);

}

package Domain;

public class OneKindAnimalPackage {
    private int one_package_id;
    private String partType;

    public OneKindAnimalPackage(int one_package_id, String partType){
        this.one_package_id = one_package_id;
        this.partType = partType;
    }

    public int getOne_package_id() {
        return one_package_id;
    }

    public void setOne_package_id(int one_package_id) {
        this.one_package_id = one_package_id;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }
}

package com.adamly.xin6.dataobject;

public class SequenceDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequence_info.name
     *
     * @mbg.generated Fri Oct 25 20:20:00 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequence_info.current_value
     *
     * @mbg.generated Fri Oct 25 20:20:00 CST 2019
     */
    private Integer currentValue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sequence_info.step
     *
     * @mbg.generated Fri Oct 25 20:20:00 CST 2019
     */
    private Integer step;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequence_info.name
     *
     * @return the value of sequence_info.name
     *
     * @mbg.generated Fri Oct 25 20:20:00 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequence_info.name
     *
     * @param name the value for sequence_info.name
     *
     * @mbg.generated Fri Oct 25 20:20:00 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequence_info.current_value
     *
     * @return the value of sequence_info.current_value
     *
     * @mbg.generated Fri Oct 25 20:20:00 CST 2019
     */
    public Integer getCurrentValue() {
        return currentValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequence_info.current_value
     *
     * @param currentValue the value for sequence_info.current_value
     *
     * @mbg.generated Fri Oct 25 20:20:00 CST 2019
     */
    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sequence_info.step
     *
     * @return the value of sequence_info.step
     *
     * @mbg.generated Fri Oct 25 20:20:00 CST 2019
     */
    public Integer getStep() {
        return step;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sequence_info.step
     *
     * @param step the value for sequence_info.step
     *
     * @mbg.generated Fri Oct 25 20:20:00 CST 2019
     */
    public void setStep(Integer step) {
        this.step = step;
    }
}
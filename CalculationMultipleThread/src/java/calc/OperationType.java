/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;

/**
 *
 * @author homek
 */
public enum OperationType {
    ADD("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/");
    
    private String stringValue;
    
    private OperationType(String stringValue){
        this.stringValue = stringValue;
    }
    
    public String getStringValue(){
        return stringValue;
    }
}

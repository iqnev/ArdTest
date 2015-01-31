/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcomunication;

/**
 *
 * @author iqnev
 */
public abstract class Command {

    private CommandIndeficator ind; //EnumClass

    protected Command(CommandIndeficator ind) {
        this.ind = ind;
    }

    public CommandIndeficator getCommandIndeficator() {
        return  this.ind;
    }
    
    /**
     * return byte for command data 
     * @return byte
     */
    public abstract byte getData();

}

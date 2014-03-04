/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.datahack.parkingdb.api;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 *
 * @author djabry
 */
@Provider
public class HeaderWriter implements WriterInterceptor {

    @Override
    public void aroundWriteTo(WriterInterceptorContext responseContext) {
        
        try {
            responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
            responseContext.proceed();
        } catch (IOException ex) {
            Logger.getLogger(HeaderWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WebApplicationException ex) {
            Logger.getLogger(HeaderWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

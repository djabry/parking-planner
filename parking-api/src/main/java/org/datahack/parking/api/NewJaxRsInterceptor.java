/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.datahack.parking.api;

import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 *
 * @author djabry
 */
//@Provider
public class NewJaxRsInterceptor implements  WriterInterceptor {

   

    @Override
    public void aroundWriteTo(WriterInterceptorContext responseContext) {
    }
    
}

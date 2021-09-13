package com.csw.aparito;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public abstract class ContextMocker extends FacesContext {
    private ContextMocker() {}

    private static class Release implements Answer<Void> {
        @Override
        public Void answer(InvocationOnMock invocation) throws Throwable {
            setCurrentInstance(null);
            return null;
        }
    }

    public static FacesContext mockFacesContext(ExternalContext externalContext) {
        FacesContext context = Mockito.mock(FacesContext.class);
        setCurrentInstance(context);
        return context;
    }

}
package com.geekerstar.monitor.endpoint;

import com.geekerstar.common.annotation.GeekEndpoint;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;

import java.util.List;

/**
 * @author geekerstar
 * @date 2020/2/1 11:52
 * @description
 */
@GeekEndpoint
public class GeekHttpTraceEndpoint {

    private final HttpTraceRepository repository;

    public GeekHttpTraceEndpoint(HttpTraceRepository repository) {
        this.repository = repository;
    }

    public GeekHttpTraceDescriptor traces() {
        return new GeekHttpTraceDescriptor(this.repository.findAll());
    }

    public static final class GeekHttpTraceDescriptor {

        private final List<HttpTrace> traces;

        private GeekHttpTraceDescriptor(List<HttpTrace> traces) {
            this.traces = traces;
        }

        public List<HttpTrace> getTraces() {
            return this.traces;
        }
    }
}

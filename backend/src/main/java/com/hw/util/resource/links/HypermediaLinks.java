package com.hw.util.resource.links;

import org.springframework.hateoas.Link;

import java.util.List;

public interface HypermediaLinks<T> {
    List<Link> getLinks(Long id);

    List<Link> getLazyProjectionLinks(Long id);

}

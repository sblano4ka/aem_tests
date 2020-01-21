package com.sblano4ka;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AemContextExtension.class)
public class ResourceResolverMapTest {

    AemContext context = new AemContext(ResourceResolverType.JCR_OAK);

    @ParameterizedTest
    @CsvSource({
            "/content/app/page?param1=value&value, /content/app/page?param1=value&value",
            "/content/app/page?param1=value&value&param2=value2, /content/app/page?param1=value&value&param2=value2",
            "/content/app/page?param1=value:value, /content/app/page?param1=value:value",
            "/content/dam/app/img.jpg?param1=value:value, /content/dam/app/img.jpg?param1=value:value",
            "/content/dam/app/img.jpg?param1=value+value, /content/dam/app/img.jpg?param1=value+value",
            "/content/dam/app/img.jpg?param1=value%value, /content/dam/app/img.jpg?param1=value%value",
            "/content/dam/app/img.jpg?param1=value value, /content/dam/app/img.jpg?param1=value value",
            "/content/dam/app/img%20name.jpg?param1=value value, /content/dam/app/img name.jpg?param1=value value",
            "/content/dam/app%202/img.jpg?param1=value value, /content/dam/app 2/img.jpg?param1=value value",
            "/content/dam/app&2/img.jpg?param1=value value, /content/dam/app&2/img.jpg?param1=value value"
    })
    void mapTest(String expected, String path) {
        ResourceResolver resourceResolver = context.resourceResolver();
        assertEquals(expected, resourceResolver.map(path));
    }
}

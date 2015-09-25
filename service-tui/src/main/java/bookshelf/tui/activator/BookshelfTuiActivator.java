package bookshelf.tui.activator;

import bookshelf.tui.BookshelfServiceProxy;
import bookshelf.tui.BookshelfServiceProxyImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Hashtable;

/**
 *
 */
public class BookshelfTuiActivator implements BundleActivator {

    /**
     * @param bundleContext
     * @throws Exception
     */
    @Override
    public void start(BundleContext bundleContext) throws Exception {

        Hashtable props = new Hashtable();

        props.put("osgi.command.scope", BookshelfServiceProxy.SCOPE);
        props.put("osgi.command.function", BookshelfServiceProxy.FUNCTIONS);

        bundleContext.registerService(BookshelfServiceProxyImpl.class.getName(),
                new BookshelfServiceProxyImpl(bundleContext), props);

    }

    /**
     * @param bundleContext
     * @throws Exception
     */
    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}

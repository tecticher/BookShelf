package bookshelf.inventory.impl.mock.activator;

import bookshelf.inventory.api.BookInventory;
import bookshelf.inventory.impl.mock.BookInventoryMockImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * Created by Administrador on 29/08/2015.
 */
public class BookInventoryMockImplActivator implements BundleActivator {

    private ServiceRegistration reg = null;

    public void start(BundleContext bundleContext) throws Exception {

        reg = bundleContext.registerService(BookInventory.class.getName(), new BookInventoryMockImpl(), null);
    }

    public void stop(BundleContext bundleContext) throws Exception {

        if (reg != null) {
            bundleContext.ungetService(reg.getReference());
            reg = null;
        }
    }
}

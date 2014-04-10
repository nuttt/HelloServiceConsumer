package helloserviceconsumer;

import ihelloservice.HelloService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		ServiceReference[] serviceReferences = bundleContext.getAllServiceReferences(null, null);
		for (ServiceReference serviceReference : serviceReferences) {
			System.out.println(serviceReference.toString());
			if(serviceReference.toString().equals("[ihelloservice.HelloService]")) {
//				System.out.println(serviceReference.toString());
				HelloService h = bundleContext.getService(serviceReference);
				System.out.println(h.getHello());
				bundleContext.ungetService(serviceReference);
			}
		}
		ServiceReference ref = bundleContext.getServiceReference(HelloService.class.getName());
		HelloService hello = bundleContext.getService(ref);
		System.out.println(hello.getHello());
		bundleContext.ungetService(ref);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}

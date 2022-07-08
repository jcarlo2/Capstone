package retail.model.facade;

import retail.model.service.Calculate;
import retail.model.service.Creator;
import retail.model.service.Finder;

public interface Facade {
    Creator creator = new Creator();
    Calculate calculate = new Calculate();
    Finder finder = new Finder();
}

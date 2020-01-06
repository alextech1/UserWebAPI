import { TestBed } from '@angular/core/testing';

import { OrderStatusService } from './orderstatus.service';

describe('OrderstatusService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OrderStatusService = TestBed.get(OrderStatusService);
    expect(service).toBeTruthy();
  });
});

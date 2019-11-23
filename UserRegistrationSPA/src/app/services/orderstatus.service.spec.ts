import { TestBed } from '@angular/core/testing';

import { OrderstatusService } from './orderstatus.service';

describe('OrderstatusService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: OrderstatusService = TestBed.get(OrderstatusService);
    expect(service).toBeTruthy();
  });
});

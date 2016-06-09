simulation step:
- sort particles into cells
- update cell
  - count particles in cell
  - calc pressure from particle count
  - update flow rate in each direction, using current pressure and last cell pressures


cell:
- flow rate in each direction
- particles ?
- pressure

particle:
- position :Vector2D
- velocity

world:
- particles
- cells
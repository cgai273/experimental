from c2 import BanditModel
import numpy as np


class EpsilonGreedy:
    def __init__(self, low, high, steps, arm_count):
        super()
        self.epsilons_ = np.linspace(low, high, num=steps)
        self.instances = self.epsilons_.size
        self.value_ = np.zeros((self.instances, arm_count))
        self.action_picks_ = np.zeros((self.instances, arm_count))
        self.steps_ = 0
        self.total_rewards_ = np.zeros((self.instances,))
        self.row_range_ = np.arange(self.instances)

    def get_actions(self, available_actions):
        r = np.random.rand(self.instances)
        r[r >= self.epsilons_] = 1
        r[r < self.epsilons_] = 0
        action_picks = (1 - r) * np.random.randint(0, available_actions, size=(self.instances,)) + r * self.value_.argmax(axis=1)
        action_picks = action_picks.astype(dtype=int)
        self.action_picks_[self.row_range_, action_picks] += 1
        self.steps_ += 1
        return action_picks

    def update_values(self, rewards, actions):
        discounted_update = (rewards - self.value_[self.row_range_, actions])
        self.value_[self.row_range_, actions] = self.value_[self.row_range_, actions] + discounted_update / self.action_picks_[self.row_range_, actions]
        self.total_rewards_ += rewards

    def get_data_point(self):
        return self.epsilons_, self.total_rewards_ / self.steps_

